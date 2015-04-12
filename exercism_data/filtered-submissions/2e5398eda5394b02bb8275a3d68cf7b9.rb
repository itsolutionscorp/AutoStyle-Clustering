#!/usr/bin/env ruby -w

class Hamming
  def Hamming.compute(a, b)
    # Gestisco stringhe di dimensione diversa.
    case a.length <=> b.length
      when -1 then b = b[0..(a.length - 1)]
      when 1 then a = a[0..(b.length - 1)]
    end

    # Faccio il confronto fra le due.
    distance = 0
    for i in 0..(a.length - 1) do
      distance += 1 if a[i] != b[i]
    end

    return distance
  end
end
