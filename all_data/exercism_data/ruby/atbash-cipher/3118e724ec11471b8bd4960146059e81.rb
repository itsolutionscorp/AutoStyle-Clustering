module Atbash
  Plain  =  'abcdefghijklmnopqrstuvwxyz'
  Cipher =  'zyxwvutsrqponmlkjihgfedcba'

  class << self

    def encode plain_text
      stream = plain_text.gsub(/[' '\,\.]/,'').downcase
      stream = stream.tr(Atbash::Cipher, Atbash::Plain).chars
      stream.each_slice(5).each_with_object(' ') do |a, cipher|
        cipher << a.join << " "
      end.strip
    end
  end

end
