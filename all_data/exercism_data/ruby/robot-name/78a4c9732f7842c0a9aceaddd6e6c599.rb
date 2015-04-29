class Robot

  attr_reader :name

  @@uniq = 0

  def initialize
    n = @@uniq/1000
    @name = "#{(n/26 + 65).chr}#{(n%26 + 65).chr}#{@@uniq/100%10}#{@@uniq/10%10}#{@@uniq%10})"
    @@uniq += 1
  end

  def reset
    initialize
  end

end
