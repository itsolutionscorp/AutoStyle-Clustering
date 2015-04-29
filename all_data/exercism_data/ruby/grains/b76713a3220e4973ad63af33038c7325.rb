class Grains
  # Note: This version is not designed for read-ablity
  # It works using Ruby's builtin binary literal

  def square(index)
    raise ArgumentError unless (1..64).include?(index)
    binary_str = '0'*64
    binary_str[index-1] = '1'
    eval('0b'+binary_str.reverse)
  end

  def total
    eval('0b'+('1'*64))  
  end
end
