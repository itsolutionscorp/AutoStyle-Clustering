class Binary
  attr_reader :binary

  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    return 0 if invalid_binary?
    binary.chars.reverse.each_with_index.inject(0) do |sum,(value, place)|
      sum + value.to_i * 2 ** place 
    end
  end

  private

  def invalid_binary?
    @binary !~ /^[0-9]+$/ 
  end

end
