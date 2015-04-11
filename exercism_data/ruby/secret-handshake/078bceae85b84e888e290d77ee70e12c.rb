class Array
  def ignore
    self
  end
end

class SecretHandshake

  CYPHER = {8 => "jump", 4 => "close your eyes",
            2 => "double blink", 1 => "wink"}

  def initialize(input)
    @meth = :ignore
    @num = input.is_a?(Fixnum) ?
      (input > 16 ? input-16 : (@meth = :reverse; input)) : 0
  end

  def commands
    CYPHER.each_pair.with_object(Array.new) do |(key, value), result|
      temp, @num = @num.divmod(key)
      result << value unless temp.zero?
    end.send(@meth)
  end
end
