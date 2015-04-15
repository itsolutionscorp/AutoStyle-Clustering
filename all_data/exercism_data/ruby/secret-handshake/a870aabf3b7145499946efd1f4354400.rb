class SecretHandshake

  SECRETS = {
    '1' => 'wink',
    '10' => 'double blink',
    '100' => 'close your eyes',
    '1000' => 'jump'
  }

  def initialize(decimal)
    @binary = decimal.to_i.to_s(2)
  end

  def commands
    @binary.reverse.each_char.with_index.with_object([]) do |(char, index), result|
      if char == '1'
        result.reverse! if index == 4
        result << SECRETS.values[index]
      end
    end.compact
  end

end
