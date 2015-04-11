# Provides string translation.
#
# (Note: In practice, I'd use the i18n gem.  This is just a stub for exercism.io.
#  It's intentionally incomplete, and I'm not trying to re-invent the wheel.)
module I18n
  DEFAULT_LOCALE = 'en'

  TRANSLATIONS = {
    'en' => {
      'bob' => {
        'responses' => {
          'blank' => 'Fine. Be that way!',
          'yelling' => 'Woah, chill out!',
          'question' => 'Sure.',
          'default' => 'Whatever.'
        }
      }
    }
  }

  # Given a String +key+, returns the string corresponding to that key in the
  # current locale.  This method mimics the necessary parts of the i18n gem API
  def translate(key)
    # Splits up the dot-separated key and walks the translations hash with
    # each part of the key.  For instance, if the translations hash contains
    # {"bob" => {"responses" => {"blank" => "Blank response."}}}, then
    # the key "bob.responses.blank" returns "Blank response."
    key.to_s.split('.').reduce(translations) { |hash, key_part| hash[key_part] }
  end
  module_function :translate

  alias :t :translate
  module_function :t

  private

  # Given a +locale+, returns the translation hash for that locale
  def self.translations(locale=DEFAULT_LOCALE)
    TRANSLATIONS[locale]
  end
end

# Represents people named Bob who have very limited conversational skills.
class Bob

  # Produces one of four responses based on a String +message+, in order of
  # decreasing precedence:
  #
  # Blank response ("Fine. Be that way!")::
  #   Returned when +message+ is the empty string or contains all whitespace.
  # Yelling response ("Woah, chill out!")::
  #   Returned when +message+ has no lowercase letters.
  # Question response ("Sure.")::
  #   Returned when +message+ ends in a question mark.
  # Default response ("Whatever.")::
  #   Returned when none of the above applies
  def hey(message)
    message = message.strip # Ignore leading and trailing whitespace in message

    if blank?(message)
      I18n.t 'bob.responses.blank'
    elsif yelling?(message)
      I18n.t 'bob.responses.yelling'
    elsif question?(message)
      I18n.t 'bob.responses.question'
    else
      I18n.t 'bob.responses.default'
    end
  end

  private

  # Returns true if +message+ contains at least one non-whitespace character
  # and no lowercase letters.  Note: messages such as "1, 2, 3" are yelling by
  # this definition.
  def yelling?(message)
    message.upcase == message && !message.empty?
  end

  # Returns true if +message+ ends with a question mark.
  def question?(message)
    message.end_with?('?')
  end

  # Returns true if +message+ is empty or contains only whitespace characters.
  def blank?(message)
    message.empty?
  end

end
