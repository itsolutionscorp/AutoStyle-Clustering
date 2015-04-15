# Clase leap year
class Year
  def self.leap?(anio)
    if (anio % 4 == 0 && anio % 100 != 0) || anio % 400 == 0
      true
    end
  end
end
