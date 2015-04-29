class PigLatin

  VOWELS       = %w(a e i o u)
  CONSONANTS   = ("a".."z").to_a - VOWELS
  EDGE_CASES   = %w(sch thr th qu ch)
  FIXED        = %w(xr yt)
  ENDING       = "ay"

  attr_writer :text

  def initialize(text)
    @text = text
  end

  def self.translate(text)
    return "" if text.empty?
    text.gsub(/\w+/) do |word|
      self.translate_word(word)
    end
  end

  private
  def self.translate_word(word)
    case
    when self.starts_with_vowel?(word) || self.fixed?(word)
      word += ENDING
    when self.edge_case?(word)
      self.translate_edge_case(word)
    when self.starts_with_consonant?(word) && self.preceeded_by_qu(word)
      self.translate_consonant_preceeded_by_qu(word)
    when self.starts_with_consonant?(word)
      word[1..-1] + word[0] += ENDING
    end
  end

  def self.translate_edge_case(word)
    edge = EDGE_CASES.detect do |edge| 
      word.start_with?(edge)
    end
    word.gsub(edge, "") + edge + ENDING
  end

  def self.translate_consonant_preceeded_by_qu(word)
    word[(3..-1)] + word[(0..2)] + ENDING
  end

  def self.fixed?(word)
    FIXED.any? do |fixed|
      word.start_with?(fixed)
    end
  end

  def self.edge_case?(word)
    EDGE_CASES.any? do |edge|
      word.start_with?(edge)
    end
  end

  def self.preceeded_by_qu(word)
    word[(1..2)] == "qu"
  end

  def self.starts_with_consonant?(word)
    CONSONANTS.include?(word[0])
  end

  def self.starts_with_vowel?(word)
    VOWELS.include?(word[0])
  end

end
