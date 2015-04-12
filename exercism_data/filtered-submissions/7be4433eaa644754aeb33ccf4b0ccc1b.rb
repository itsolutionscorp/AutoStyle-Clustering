class Hamming

  def compute(var1, var2)
    if var1.length === 1 && var2.length === 1
      var1 === var2 ? 0 : 1
    elsif var1.length === 1 || var2.length === 1
      case var1.length
      when 1
        var1[0] === var2[0] ? 0 : 1
      else
        var1[0] === var2[0] ? 0 : 1
      end
    elsif var1.is_a?(Array)
      self.compute(var1[0], var2[0]) + self.compute(var1[1, var1.length], var2[1, var2.length])
    else
      self.compute(var1.split('')[0], var2.split('')[0]) + self.compute(var1.split('')[1, var1.length], var2.split('')[1, var2.length])
    end
  end
end
