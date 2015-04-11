module Atbash
  PLAIN = [*'a'..'z'].join
  CIPHER = PLAIN.reverse

  class << self
    def encode(string, size=5)
      normalized = normalize(string)
      encryption = encrypt(normalized)
      group_by(encryption, size)
    end

    def normalize(string)
      string.gsub(/\W/,"").downcase
    end

    def encrypt(string)
      string.tr(PLAIN, CIPHER)
    end

    private

    def group_by(string, size)
      string.scan(/.{1,#{size}}/).join(' ')
    end
  end
end
