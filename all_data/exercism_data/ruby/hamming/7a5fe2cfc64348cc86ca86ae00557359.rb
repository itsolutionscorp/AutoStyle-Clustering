class Hamming
  def self.compute(first, second)
    if first[0] == second[0]
      hamnu = 0
    end
    if first[0] != second[0]
      hamnu = 1
    end
    if first[1] != second[1]
      hamnu +=1
    end
    if first[2] != second[2]
      hamnu +=1
    end
    if first[3] && second[3]
      hamnu +=1 if first[3] != second[3]
    end
     if first[4] && second[4]
      hamnu +=1 if first[4] != second[4]
    end
    if first[5] && second[5]
      hamnu +=1 if first[5] != second[5]
    end
    if first[6] && second[6]
      hamnu +=1 if first[6] != second[6]
    end
    if first[7] && second[7]
      hamnu +=1 if first[7] != second[7]
    end
    if first[8] && second[8]
      hamnu +=1 if first[8] != second[8]
    end
    if first[9] && second[9]
      hamnu +=1 if first[9] != second[9]
    end
    if first[10] && second[10]
      hamnu +=1 if first[10] != second[10]
    end
    if first[11] && second[11]
      hamnu +=1 if first[11] != second[11]
    end
    return hamnu
  end
end
