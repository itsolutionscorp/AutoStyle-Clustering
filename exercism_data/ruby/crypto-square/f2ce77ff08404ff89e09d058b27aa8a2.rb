class Crypto

  attr_reader :message

  def initialize message
    @message = message
  end

  def normalize_plaintext
    message.downcase.delete "^a-z0-9"
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    plaintext_slices.map(&:join)
  end

  def ciphertext
    plaintext_slices.each_with_object(Hash.new {|hash, index| hash[index] = ""}) do |i, hash|
      i.each_with_index do |char, n|
        hash[n] << char
      end
    end.values.join
  end

  def normalize_ciphertext
    ciphertext.chars.each_slice(size).map(&:join).join(" ")
  end

  private

  def plaintext_slices
    normalize_plaintext.chars.each_slice(size)
  end
end
