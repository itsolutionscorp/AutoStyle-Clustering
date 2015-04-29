class Anagram
  attr_accessor :input

  def initialize(input)
    @input = input
  end

  def match(word_list)
    word_list.map! { |word|
      dword = word.downcase
      input = @input.downcase

      input.split('').sort == dword.split('').sort &&
      input != dword ?
      word : nil
    }.compact!
  end
end
