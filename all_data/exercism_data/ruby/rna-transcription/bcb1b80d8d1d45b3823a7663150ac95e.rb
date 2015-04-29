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
      args[0].chars.reduce("") do |sum, el|
        sum += const_get("#{$1.upcase}_MAP")[el.to_sym].to_s
      end
    end
  end

end
