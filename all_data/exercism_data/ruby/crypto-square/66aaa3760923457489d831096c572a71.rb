class Crypto
  attr_reader :normalize_plaintext, :size, :plaintext_segments, :ciphertext, 
    :normalize_ciphertext

  def initialize string
    @normalize_plaintext = string.downcase.gsub /[^\w]/, ''
    @size = Math.sqrt(@normalize_plaintext.length).ceil
    @plaintext_segments = string_slices @normalize_plaintext, @size

    padded_rectangle = @plaintext_segments.map { |x| x.ljust(@size).chars }
    @ciphertext = padded_rectangle.transpose.flatten.join.delete ' '

    column_size = (@ciphertext.length.to_f/@size).ceil
    @normalize_ciphertext = string_slices(@ciphertext, column_size).join ' '
  end

  private
    def string_slices(string, length)
      return string.chars.each_slice(length).map &:join
    end
end
