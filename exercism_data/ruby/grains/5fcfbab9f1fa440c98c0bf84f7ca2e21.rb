class Grains
  def square (lado)
    $cont = 1
    $i = 2
    until $i > lado do 
      $cont *= 2
      $i += 1
    end
    return $cont
  end

  def total
    return square(lado)
  end
end
