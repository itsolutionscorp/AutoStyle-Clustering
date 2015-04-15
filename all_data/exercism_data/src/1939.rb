def compute(base, another)
    different = 0
    index     = 0
    loop do
      base_char    = base[index]
      another_char = another[index]
      break if base_char.nil? || another_char.nil?
      different += 1 unless base_char == another_char
      index += 1
    end
    different
  end