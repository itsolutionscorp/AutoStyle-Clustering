class Raindrops

  def convert(number)
    plingify(number)
    plangify(number)
    plongify(number)

    if @pling || @plang || @plong
      return "#{@pling}#{@plang}#{@plong}"
    else
      return "#{number}"
    end
  end

  def plingify(number)
    @pling = nil
    if number % 3 == 0
      @pling = "Pling"
    end
  end

  def plangify(number)
    @plang = nil
    if number % 5 == 0
      @plang = "Plang"
    end
  end

  def plongify(number)
    @plong = nil
    if number % 7 == 0 
      @plong = "Plong"
    end
  end

end
