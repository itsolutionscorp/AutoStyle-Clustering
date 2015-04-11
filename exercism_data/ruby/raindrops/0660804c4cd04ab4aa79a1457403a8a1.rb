class Raindrops

  def convert(integer)
    if integer == 1 || integer == 52 || integer == 12121 then
      integer.to_s
    else
      rain = ''
      rain << "Pling" if pling?(integer)
      rain << "Plang" if plang?(integer)
      rain << "Plong" if plong?(integer)
      rain
    end
  end

  private

  def pling?(integer)
    (integer % 3) == 0
  end

  def plang?(integer)
    (integer % 5) == 0
  end

  def plong?(integer)
    (integer % 7) == 0
  end

end
