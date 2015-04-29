class Crypto
  attr_reader :text

  alias_method :normalize_plaintext, :text

  def initialize(str)
    self.text = str
  end

  def text=(str)
    @text = normalize(str)
    @size = nil
    @plaintext_segments = nil
    @ciphertext = nil
    @normalized_ciphertext = nil
  end

  def size
    @size ||= Math.sqrt(text.length).ceil
  end

  def plaintext_segments
    @plaintext_segments ||= size.times.reduce([]) do |segments, i|
      segments << text.slice(i * size, size)
    end.compact.reject(&:empty?)
  end

  def ciphertext
    generate_ciphertext
  end

  def normalize_ciphertext
    generate_ciphertext(' ')
  end

  private

  def generate_ciphertext(sep = '')
    # split segments to arrays of characters
    temp = plaintext_segments.map { |s| s.split('') }
    # pad last segment so all inner arrays are equal
    temp.last << '' while temp.last.length < size
    # transpose swaps columns and rows, just what we want!
    # then we glue it back together from an array of arrays of chars to a string
    temp.transpose.map(&:join).join(sep)
  end

  def normalize(str)
    str.gsub(/\W/, '').downcase
  end
end
