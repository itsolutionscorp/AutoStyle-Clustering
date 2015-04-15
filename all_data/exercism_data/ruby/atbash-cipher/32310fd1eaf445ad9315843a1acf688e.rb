class Atbash

  PLAIN =  {'a' => 'z',
            'b' => 'y',
            'c' => 'x',
            'd' => 'w',
            'e' => 'v',
            'f' => 'u',
            'g' => 't',
            'h' => 's',
            'i' => 'r',
            'j' => 'q',
            'k' => 'p',
            'l' => 'o',
            'm' => 'n',
            'n' => 'm',
            'o' => 'l',
            'p' => 'k',
            'q' => 'j',
            'r' => 'i',
            's' => 'h',
            't' => 'g',
            'u' => 'f',
            'v' => 'e',
            'w' => 'd',
            'x' => 'c',
            'y' => 'b',
            'z' => 'a',
            '0' => '0',
            '1' => '1',
            '2' => '2',
            '3' => '3',
            '4' => '4',
            '5' => '5',
            '6' => '6',
            '7' => '7',
            '8' => '8',
            '0' => '9'}

  def self.encode(input_string)
    characters = input_string.downcase.gsub(/[^a-z0-9]+/,'').chars
    characters.map do |char|
      PLAIN[char]
    end.join.scan(/.{1,5}/).join(' ')
  end

end
