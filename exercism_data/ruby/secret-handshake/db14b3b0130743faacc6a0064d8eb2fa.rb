class SecretHandshake
  attr_reader :num, :binary

  SECRETS = %w(wink double\ blink close\ your\ eyes jump)

  def initialize(input)
    @num = input.kind_of?(Integer) ? input : 0
  end

  def binary
    num.to_s(2)
  end

  def commands
    @commands ||= get_commands
  end

  def formatted_binary
    binary.split(//).map(&:to_i).reverse  ### "1010" => [0, 1, 0, 1] 
  end

  def get_commands
    ary = []
    formatted_binary.each_with_index do |num, i|
      ary << SECRETS[i] if num == 1
    end
    ary.include?(nil) ? ary.compact.reverse : ary
  end
end
