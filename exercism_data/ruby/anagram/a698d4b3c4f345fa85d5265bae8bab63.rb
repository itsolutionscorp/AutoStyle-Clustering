class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
    same_length  = list.select { |word| word.length == @word.length }
    same_length.delete_if { |word| word.downcase == @word }

    @word.each_char.to_a.each do |letter|
      delete_non_matching_letters(same_length, letter)
      delete_with_duplicate_letters(same_length, letter)
    end

    same_length.sort
  end

  def delete_non_matching_letters(same_length, letter)
      same_length.delete_if { |word| !word.downcase.include?(letter.downcase) }
  end

  def delete_with_duplicate_letters(same_length, letter)
      same_length.delete_if { |word| word.count(letter) > @word.count(letter) }
  end
end
