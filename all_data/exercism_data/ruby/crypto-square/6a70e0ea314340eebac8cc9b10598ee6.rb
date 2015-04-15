class Crypto
  def initialize(msg)
    @msg = msg
  end

  def normalize_plaintext
    normalize @msg
  end

  def ciphertext
    normalize encrypt_msg
  end

  def normalize_ciphertext
    segments(ciphertext, cipher_size).join(' ')
  end

  def size
    Math.sqrt(Math.sqrt(length).ceil**2).to_i
  end

  def plaintext_segments
    segments normalize_plaintext, size
  end

  private

  def encrypt_msg
    slices(rectangle_padded_msg, size).transpose.flatten.join
  end

  def length
    normalize_plaintext.length
  end

  def cipher_size
    (length.to_f / size).ceil
  end

  def normalize(msg)
    msg.scan(/\w/).join.downcase
  end

  def rectangle_padded_msg
    nb =  size * cipher_size - length
    normalize_plaintext + ' ' * nb
  end

  def slices(msg, slice_size)
    msg.chars.each_slice(slice_size).to_a
  end

  def segments(msg, segment_size)
    slices(msg, segment_size).map(&:join)
  end
end
