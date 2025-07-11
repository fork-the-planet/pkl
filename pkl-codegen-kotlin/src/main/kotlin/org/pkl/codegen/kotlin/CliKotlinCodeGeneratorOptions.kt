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
package org.pkl.codegen.kotlin

import java.nio.file.Path
import org.pkl.commons.cli.CliBaseOptions

/** Configuration options for [CliKotlinCodeGenerator]. */
data class CliKotlinCodeGeneratorOptions(
  /** Base options shared between CLI commands. */
  val base: CliBaseOptions,

  /** The directory where generated source code is placed. */
  val outputDir: Path,

  /** The characters to use for indenting generated source code. */
  val indent: String = "  ",

  /** Whether to preserve Pkl doc comments by generating corresponding KDoc comments. */
  val generateKdoc: Boolean = false,

  /** Whether to generate config classes for use with Spring Boot. */
  val generateSpringBootConfig: Boolean = false,

  /** Whether generated classes should implement [java.io.Serializable]. */
  val implementSerializable: Boolean = false,

  /** Whether to add the `@Generated` annotation to types */
  val addGeneratedAnnotation: Boolean = false,

  /**
   * A rename mapping for class names.
   *
   * When you need to have Kotlin class or package names different from the default names derived
   * from Pkl module names, you can define a rename mapping, where the key is a prefix of the
   * original Pkl module name, and the value is the desired replacement.
   */
  val renames: Map<String, String> = emptyMap(),
) {
  @Suppress("DeprecatedCallableAddReplaceWith")
  @Deprecated("deprecated without replacement")
  fun toKotlinCodegenOptions(): KotlinCodeGeneratorOptions = toKotlinCodeGeneratorOptions()

  internal fun toKotlinCodeGeneratorOptions(): KotlinCodeGeneratorOptions =
    KotlinCodeGeneratorOptions(
      indent,
      generateKdoc,
      generateSpringBootConfig,
      implementSerializable,
      addGeneratedAnnotation,
      renames,
    )
}
