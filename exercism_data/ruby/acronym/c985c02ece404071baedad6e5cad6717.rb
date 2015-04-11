require 'pry'

module Acronym
  def self.abbreviate long_name
    word_array = isolate_seperated_words(long_name)
    acronym_array = extract_acronym_letters(word_array)
    acronym_array.join
  end

  def self.isolate_seperated_words string
    string.split(/[\s\-,:]/).reject(&:empty?)
  end

  def self.extract_acronym_letters word_array
    word_array.map do |word|
      filter_lower_case(
        first_letter_upcase(
          filter_subsequent_caps word))
    end
  end

  def self.filter_subsequent_caps string
    string.gsub(/\A([A-Z])[A-Z]+/, '\1')
  end

  def self.first_letter_upcase string
    string[0] = string[0].upcase
    string
  end

  def self.filter_lower_case string
    string.tr('a-z', '')
  end
end
