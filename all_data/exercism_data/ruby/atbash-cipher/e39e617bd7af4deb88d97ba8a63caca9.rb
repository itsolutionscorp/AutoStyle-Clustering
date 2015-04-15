class Atbash
  def self.encode(plain)
    new(plain).encode
  end

  def initialize(plain)
    @plain = plain
  end

  def encode
    portion crypt normalize @plain
  end

  private

  def normalize(text)
    text.downcase.gsub(/\W/, '')
  end

  PLAIN = "abcdefghijklmnopqrstuvwxyz"
  CRYPT = PLAIN.reverse

  def crypt(text)
    text.tr(PLAIN, CRYPT)
  end

  # Positive lookahead stolen from:
  # http://stackoverflow.com/questions/1078347/is-there-a-rails-trick-to-adding-commas-to-large-numbers/11466770#11466770 
  # alternative was:
  # text.gsub(/(\w{5})/, '\1 ').rstrip
  def portion(text)
    text.gsub(/(\w{5})(?=\w)/, '\1 ')
  end
end
