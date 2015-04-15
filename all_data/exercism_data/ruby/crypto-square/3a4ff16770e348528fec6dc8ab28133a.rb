class Crypto
  private
  include Math
  attr_accessor :plaintext
  def initialize(val)
    self.plaintext =val
  end

  def transposed_plaintext
    plaintext_segments.map{|x| x.chars+['']*(size-x.chars.size)}.transpose.map(&:join)
  end

  def row_size
    normalize_plaintext.size/size
  end

  public
  def normalize_plaintext
    plaintext.gsub(/\W+/,'').downcase
  end

  def size
    sqrt(normalize_plaintext.size).to_i**2==normalize_plaintext.size ? sqrt(normalize_plaintext.size).to_i : sqrt(normalize_plaintext.size).to_i+1
  end

  def plaintext_segments
    normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def ciphertext
    transposed_plaintext.join
  end

  def normalize_ciphertext
    transposed_plaintext.join.scan(/.{1,#{transposed_plaintext.first.size}}/).join(' ')
  end
end
