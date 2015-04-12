class Hamming
  def compute(arg1, arg2)
    array1 = arg1.split(//)
    array2 = arg2.split(//)

    if array1.count == array2.count
      if array1 == array2
        0
      elsif
        max = array1.count
        i = 0
        ungleichheiten = 0
        while i < max
          unless array1.first == array2.first
            ungleichheiten += 1
          end
          array1.delete_at(0)
          array2.delete_at(0)
          i += 1
        end
        ungleichheiten
      end
    else
      puts "Fehler: Zwei unterschiedlich lange Eingaben"
    end
  end
end
