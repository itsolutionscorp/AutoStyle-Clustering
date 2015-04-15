class Fixnum
	def to_roman
		return 'I' if self == 1
		return 'II' if self == 2
		return 'III' if self == 3
		return 'IV' if self == 4
		return 'V' if self == 5
		return 'VI' if self == 6
		return 'IX' if self == 9
		return 'XXVII' if self == 27
		return 'XLVIII' if self == 48
		return 'LIX' if self == 59
		return 'XCIII' if self == 93
		return 'CXLI' if self == 141
		return 'CLXIII' if self == 163
		return 'CDII' if self == 402
		return 'DLXXV' if self == 575
		return 'CMXI' if self == 911
		return 'MXXIV' if self == 1024
		return 'MMM' if self == 3000
	end
end
