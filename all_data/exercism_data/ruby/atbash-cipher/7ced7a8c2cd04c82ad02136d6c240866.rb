module Atbash
  Plain  =  'abcdefghijklmnopqrstuvwxyz'
  Cipher =  'zyxwvutsrqponmlkjihgfedcba'

  class << self

    def encode text
      slice prepare(text).tr(Atbash::Cipher, Atbash::Plain).chars, 5
    end

  private
    def prepare  text
      text.gsub(/[' '\,\.]/,'').downcase
    end

    def slice sequence, n
      sequence.each_slice(n).each_with_object(' ') do |a, text|
        text << a.join << " "
      end.strip
    end

  end
end
