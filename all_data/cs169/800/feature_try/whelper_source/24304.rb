def ordenar_cadena(cadena, cases)
  if (cases == 0) then
    cadena = cadena.chars.sort.join
  else
    cadena = cadena.chars.sort { |a, b| a.casecmp(b) }.join
  end
  cadena
end

def combine_anagrams(palabras)
  i = 0
  k = 0
  l = 0
  j = 0
  arreglo = Array.new
  arreglo[l] = Array.new
  return palabras if (palabras.length == 0)
  while (i < palabras.length) do
    palabraInicial = ordenar_cadena(palabras[i], 1)
    palabraComparar = ordenar_cadena(palabras[k], 1)
    if (palabraInicial.casecmp(palabraComparar) == 0) then
      arreglo[l][j] = palabras[k]
      j = (j + 1)
    end
    k = (k + 1)
    if (k == palabras.length) then
      i = (i + 1)
      k = 0
      l = (l + 1)
      j = 0
      arreglo[l] = Array.new if (i != palabras.length)
    end
  end
  return arreglo.uniq
end

