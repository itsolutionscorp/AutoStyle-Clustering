class Bob
	def hey(speaker_voice)
		return 'Fine. Be that way!' if silent?(speaker_voice)
		return 'Woah, chill out!' if shouting?(speaker_voice)
		return 'Sure.' if asking?(speaker_voice)
		'Whatever.'
	end

	private

	def silent?(speaker_voice)
		speaker_voice.rstrip.empty?
	end

	def shouting?(speaker_voice)
		speaker_voice == speaker_voice.upcase
	end

	def asking?(speaker_voice)
		speaker_voice.end_with?('?')
	end
end
