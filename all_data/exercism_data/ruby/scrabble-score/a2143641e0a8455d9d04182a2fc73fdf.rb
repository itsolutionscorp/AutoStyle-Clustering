class Scrabble
  attr_reader :word

  def self.score(word)
    Scrabble.new(word).score
  end

  def initialize(word)
    @word = word
  end

  def score
    return 0 unless valid_word?
    calculate_sum_score
  end

  private

  def calculate_sum_score
    letters = divide_word_on_letters
    scores = score_by_letters(letters)
    scores.reduce(:+)
  end

  def divide_word_on_letters
    @word.split('')
  end

  def score_by_letters(letters)
    letters.map { |letter| value_by_letter(letter) }
  end

  def value_by_letter(letter)
    letter = letter.upcase
    case
    when 'AEIOULNRST'.include?(letter) then 1
    when 'DG'.include?(letter) then 2
    when 'BCMP'.include?(letter) then 3
    when 'FHVWY'.include?(letter) then 4
    when 'K'.include?(letter) then 5
    when 'JX'.include?(letter) then 8
    when 'QZ'.include?(letter) then 10
    else 0
    end
  end

  def valid_word?
    @word ? !@word.strip.empty? : false
  end
end
