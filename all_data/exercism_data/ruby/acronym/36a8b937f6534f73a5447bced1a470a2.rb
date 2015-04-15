module Acronym
  def self.abbreviate long_name
    String.new.tap do |acronym_str|
      each_word_in(long_name).map do |w|
        acronym_str << acronyms_in_word(w)
      end
    end
  end

  def self.each_word_in sentence
    sentence.split(/\W/)
  end

  def self.acronyms_in_word word
    word.scan(/[A-Z]+[a-z]*|\A[a-z]/).map(&:chr).join.upcase
  end
end
