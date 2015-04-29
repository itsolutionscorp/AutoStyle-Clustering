class Atbash
  PLAIN = 'abcdefghijklmnopqrstuvwxyz'.chars

  def self.encode(string, cipher='')

    string.downcase.chars.each do |c|
      if c.match(/\w+/)
        pos = PLAIN.index(c)
        if pos.nil?
          cipher += c
        else
          cipher += PLAIN.reverse[pos]
        end
      end

    end
    return cipher.scan(/.{5}|.+/).join(' ')
  end

end
