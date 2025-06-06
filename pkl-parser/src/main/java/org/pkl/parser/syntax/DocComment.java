/*
 * Copyright © 2024-2025 Apple Inc. and the Pkl project authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pkl.parser.syntax;

import java.util.List;
import org.pkl.parser.ParserVisitor;
import org.pkl.parser.Span;
import org.pkl.parser.util.Nullable;

public final class DocComment extends AbstractNode {
  private final List<Span> spans;

  public DocComment(List<Span> spans) {
    super(spans.get(0).endWith(spans.get(spans.size() - 1)), null);
    this.spans = spans;
  }

  @Override
  public Span span() {
    return spans.get(0).endWith(spans.get(spans.size() - 1));
  }

  public List<Span> getSpans() {
    return spans;
  }

  @Override
  public <T> @Nullable T accept(ParserVisitor<? extends T> visitor) {
    return visitor.visitDocComment(this);
  }

  @Override
  public String text(char[] source) {
    var builder = new StringBuilder();
    for (var span : spans) {
      builder.append(new String(source, span.charIndex(), span.length()));
      builder.append('\n');
    }
    return builder.toString();
  }
}
