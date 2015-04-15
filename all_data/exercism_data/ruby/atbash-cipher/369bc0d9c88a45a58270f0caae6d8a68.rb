class Atbash

  def self.encode plain
    plain.downcase.scan(/\w/).map do |char|
      if char[/\d/] # is a digit
        char
      else
        (25 - (char.ord - 97)*2 + char.ord).chr
      end
    end.join.scan(/.{5}|.+/).join(' ')
  end

end
