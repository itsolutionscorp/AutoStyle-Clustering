class Crypto
  attr_reader :plaintext

  def initialize(plaintext)
    @plaintext = plaintext
    normalize_plaintext
  end

  def normalize_plaintext
    @plaintext = plaintext.downcase.gsub(/\W/, '')
  end

  def size
    Math.sqrt(plaintext.length).ceil
  end

  def plaintext_segments
    plaintext_segments_array.map { |e| e.join }
  end

  def ciphertext
    padded_plaintext_segments_array.transpose.flatten.join
  end

  def normalize_ciphertext
    ciphertext.gsub(/.{#{normalized_ciphertext_chunk_size}}/) {|chunk| "#{chunk} " }.strip
  end

  private

  def plaintext_segments_array
    plaintext.chars.each_slice(size)
  end

  def padded_plaintext_segments_array
    plaintext_segments_array.map do |ary|
      ary.fill("", ary.length...size)
    end
  end

  def normalized_ciphertext_chunk_size
    # readme suggests this:
    # plaintext_segments_array.count
    # tests suggest this:
    5
  end
end
