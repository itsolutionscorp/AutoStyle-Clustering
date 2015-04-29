class Anagram
  def initialize(source)
    @source = source
  end

  def match(candidates)
    candidates.select {|candidate| valid? candidate}
  end

  private
  attr_reader :source

  def valid?(candidate)
    Frequency.of(letters_in candidate) == Frequency.of(letters_in source)
  end

  def letters_in(string)
    string.downcase.each_char
  end
end

module Frequency
  def self.of(enum)
    enum.each_with_object(Hash.new(0)) do |el, frequency|
      frequency[el] += 1
    end
  end
end
