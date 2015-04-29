class Anagram
  attr_reader :subject

  def initialize(input)
    @subject = input
  end

  def match(potentials_array)
    length_matches = size_checker(potentials_array)
    length_matches.select do |word|
      word.split(//).all? { |letter| subject.include?(letter) &&
                        subject.count(letter) == word.count(letter) }
    end
  end

private
  def size_checker(potentials_array)
    potentials_array.select do |word|
      word.length == subject.length
    end
  end
end
