class Atbash

  CODE = {
    "a" => "z", "b" => "y", "c" => "x",
    "d" => "w", "e" => "v", "f" => "u",
    "g" => "t", "h" => "s", "i" => "r",
    "j" => "q", "k" => "p", "l" => "o",
    "m" => "n", "n" => "m", "o" => "l",
    "p" => "k", "q" => "j", "r" => "i",
    "s" => "h", "t" => "g", "u" => "f",
    "v" => "e", "w" => "d", "x" => "c",
    "y" => "b", "z" => "a"
  }

  def self.encode(string)
    container = []
    string.delete(' ').strip.each_char do |char|
      if CODE.has_key?(char.downcase)
        container << CODE[char.downcase]
      else
        container << char
      end
    end
    container.join.delete(',').scan(/.{1,5}/).join(" ").delete(".").strip
  end

end
