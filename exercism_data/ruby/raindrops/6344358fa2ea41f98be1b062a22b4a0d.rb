class Raindrops
  def convert number
    @number = number
    @out = ''

    pling!
    plang!
    plong!

    output
  end

  private

  def pling!
    append_if_multiple_of 3, 'Pling'
  end

  def plang!
    append_if_multiple_of 5, 'Plang'
  end

  def plong!
    append_if_multiple_of 7, 'Plong'
  end

  def append_if_multiple_of divisor, str
    if @number % divisor == 0
      @out += str
    end
  end

  def output
    if @out.empty?
      @number.to_s
    else
      @out
    end
  end
end
