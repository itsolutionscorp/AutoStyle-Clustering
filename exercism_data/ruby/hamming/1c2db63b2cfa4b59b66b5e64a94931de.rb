class Hamming
  def self.compute(source, guess)
    incorrect_guesses = 0
    source.each_char.with_index do |source_letter, index|
      unless source_letter == guess[index]
        incorrect_guesses += 1
      end
    end
    incorrect_guesses
  end
end
