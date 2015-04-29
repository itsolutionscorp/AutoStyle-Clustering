class Crypto
  attr_reader :normalize_plaintext, :size, :plaintext_segments, :ciphertext, 
    :normalize_ciphertext

  def initialize string
    @normalize_plaintext = string.downcase.gsub /[^\w]/, ''
    
    length = normalize_plaintext.length
    @size = Math.sqrt(length).ceil

    @plaintext_segments = (0..length - 1).step(size).map do |i|
      normalize_plaintext[i, size]
    end

    padded_rectangle = plaintext_segments.map { |x| x.ljust(size).chars }
    @ciphertext = padded_rectangle.transpose.flatten.join.delete ' '

    column_size = (length.to_f/size).ceil
    @normalize_ciphertext = (0..length - 1).step(column_size).map do |i|
      ciphertext[i, column_size]
    end.join ' '
  end
end
