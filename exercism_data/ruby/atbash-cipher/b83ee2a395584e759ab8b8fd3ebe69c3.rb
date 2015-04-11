class Atbash < String
  A2Z = ('a'..'z').to_a.join
  CIPHER_MAP = [A2Z, A2Z.reverse]

  def self.encode(string)
    new(string).encode
  end

  def initialize(string)
    super(string.gsub(/[^a-z0-9]/i, '').downcase)
  end

  def encode
    tr(*CIPHER_MAP).segments.join(' ')
  end

  protected

  def segments(seg_size = 5)
    scan(/.{1,#{seg_size}}/)
  end
end
