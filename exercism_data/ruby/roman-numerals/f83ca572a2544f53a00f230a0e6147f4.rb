class Fixnum
  def to_roman
    i = self
    res = ""

    digit = ->(name, value) do
      while i >= value
        res << name
        i -= value
      end
    end

    digit['M',  1000]
    digit['CM', 900]
    digit['D',  500]
    digit['CD', 400]
    digit['C',  100]
    digit['XC', 90]
    digit['L',  50]
    digit['XL', 40]
    digit['X',  10]
    digit['IX', 9]
    digit['V',  5]
    digit['IV', 4]
    digit['I',  1]

    res
  end
end
