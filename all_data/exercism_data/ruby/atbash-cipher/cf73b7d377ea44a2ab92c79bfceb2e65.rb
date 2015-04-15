class Atbash
  class << self
    def encode(string)
      string.chars.
        map {|char| cipher[char.downcase] }.
        compact.
        each_slice(5).
        map(&:join).
        join(' ')
    end

    private

    def cipher
      @cipher ||= Hash[letters.zip(letters.reverse)].tap do |hash|
        (0..9).each {|n| hash["#{n}"] = "#{n}" }
      end
    end

    def letters
      ('a'..'z').to_a
    end
  end
end
