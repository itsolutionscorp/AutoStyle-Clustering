class Complement
  DNA_MAP={
    G: :C,
    C: :G,
    T: :A,
    A: :U
  }

  RNA_MAP = DNA_MAP.invert

  def self.method_missing(method, *args)
    if method.match(/of_(.*)/) && %w(rna dna).include?($1)
      args[0].tr(
        const_get("#{$1.upcase}_MAP").keys.join,
        const_get("#{$1.upcase}_MAP").values.join
      )
    end
  end

end
