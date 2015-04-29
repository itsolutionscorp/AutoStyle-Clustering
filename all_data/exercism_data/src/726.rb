def compute(primerCodigo , segundoCodigo)
    if primerCodigo.length == segundoCodigo.length


      (0..primerCodigo.length - 1).each do |i|
        @distancia += 1 unless primerCodigo[i] == segundoCodigo[i]
      end
    end
    @distancia = 0 if @distancia.nil?
  end