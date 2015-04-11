module Atbash
  CIPHER = Hash[[*'a'..'z'].zip([*'a'..'z'].reverse)]

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
      string.chars.map { |i| digit?(i) ? i : CIPHER[i] }.join
    end

    private

    def digit?(string)
      string[/[0-9]/]
    end

    def group_by(string, size)
      string.chars.each_slice(size).map do |slice| 
        slice.join
      end.join(' ')
    end
  end
end
