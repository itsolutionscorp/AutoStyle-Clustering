class Crypto
  def initialize(input)
    @input = input
  end

  def normalize_plaintext
    @input.split(/\W+/).join.downcase
  end

  def size
    find_next_square(normalize_plaintext.length)
  end

  def plaintext_segments
    normalize_plaintext.chars.each_slice(size).map(&:join)
  end

  def ciphertext
    generate_cipher.join
  end

  def normalize_ciphertext
    generate_cipher.join(" ")
  end

  def generate_cipher
    plaintext_segments.first.length.times.map do |i|
      plaintext_segments.map { |seg| seg[i] }.compact.join
    end
  end

  def find_next_square(n)
    squares = []
    until (squares.empty? ? 0 : squares.last.last) >= n
      squares << [squares.length + 1, (squares.length + 1) ** 2]
    end
    squares.last.first
  end
end
