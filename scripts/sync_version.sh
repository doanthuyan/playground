if [[ -n git diff origin -- .\VERSION ]]; then

	copy .\VERSION .\source\Sniffer\VERSION.h /Y;
	git commit .\source\Sniffer\VERSION.h -m "Auto sync version";
fi