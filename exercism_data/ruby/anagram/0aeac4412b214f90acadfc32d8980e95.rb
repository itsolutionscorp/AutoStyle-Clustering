class Anagram
  attr_reader :word

  def initialize(word)
    @word = word.downcase
  end

  def match(options)
    sorted_word = word.downcase.split('').sort.join('')
  
    options.select do |option|
      next if word == option.downcase
      sorted_option = option.downcase.split('').sort.join('')
      option if sorted_word == sorted_option
    end
  end

end
