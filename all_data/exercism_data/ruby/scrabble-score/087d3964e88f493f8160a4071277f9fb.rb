class Scrabble
  def self.score(word, specials = [])
    Scrabble.new(word, specials).score
  end

  POINTS = Hash.new(0)
  [
    %w(A E I O U L N R S T), 1,
    %w(D G),                 2,
    %w(B C M P),             3,
    %w(F H V W Y),           4,
    %w(K),                   5,
    %w(J X),                 8,
    %w(Q Z),                 10
  ].each_slice(2) do |p|
    letters, points = p
    letters.each { |l| POINTS[l] = points }
  end

  def initialize(word, specials = [])
    @word = word || ''
    add_bonus_letters(specials)
    @word_bonus = word_bonus(specials)
  end

  def score
    @word_bonus * @word.upcase.chars.map { |c| POINTS[c] }.reduce(0, :+)
  end

  private

  def word_bonus(specials)
    specials.map do |s|
      case s
      when :double_word then 2
      when :triple_word then 3
      else 1
      end
    end.reduce(1, :*)
  end

  def add_bonus_letters(specials)
    specials.each_with_index do |s, i|
      case s
      when :double_letter then 1
      when :triple_letter then 2
      else 0
      end.times { @word << @word[i] }
    end
  end
end

class ScrabbleTest < MiniTest::Unit::TestCase
  def test_double_letter_scores_letter_double
    assert_equal 8, Scrabble.score('f', [:double_letter])
  end

  def test_triple_letter_scores_letter_double
    assert_equal 3, Scrabble.score('a', [:triple_letter])
  end

  def test_double_word_scores_word_double
    assert_equal 12, Scrabble.score('street', [:double_word, nil, nil, nil, nil, nil])
  end

  def test_triple_word_scores_word_triple
    assert_equal 30, Scrabble.score('fish', [:triple_word, nil, nil, nil])
  end

  def test_all_specials_together
    assert_equal 114, Scrabble.score('fish', [:triple_letter, :double_letter, :double_word, :triple_word])
  end
end
