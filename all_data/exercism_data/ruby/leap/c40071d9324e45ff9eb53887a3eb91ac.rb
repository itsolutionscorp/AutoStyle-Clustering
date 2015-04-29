# encoding: UTF-8
# Determina si un anio es bisiesto
class Year
  def self.leap?(year)
    if year % 400 == 0
      return true
    elsif year % 4 == 0 && year % 100 != 0
      return true
    else
      return false
    end
  end
end
