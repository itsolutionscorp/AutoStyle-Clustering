class Crypto
  attr_reader :sentence, :size

  def initialize(sentence)
    @sentence = sentence
    @size     = calculate_nearest_perfect_square
  end

  def normalize_plaintext
    sentence.scan(/\w/).each(&:downcase!).join
  end

  def plaintext_segments
    chunk.map(&:join)
  end

  def ciphertext
    encode.join
  end

  def normalize_ciphertext
    return encode.map(&:join).join(" ") if less_than_a_full_square?
    ciphertext.scan(/.{1,#{size-1}}/).join(" ")
  end

  private
  def convert_normalize_plaintext_to_grouped_array
    normalize_plaintext.chars.each_slice(size).to_a
  end
  alias_method :chunk, :convert_normalize_plaintext_to_grouped_array

  def calculate_nearest_perfect_square
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def less_than_a_full_square?
    @size < 4
  end

  def encode
    chunk.reduce(&:zip).map(&:flatten)
  end

end
